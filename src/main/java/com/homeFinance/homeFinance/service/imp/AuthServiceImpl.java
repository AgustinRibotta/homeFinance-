package com.homeFinance.homeFinance.service.imp;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.homeFinance.homeFinance.dto.request.HouseholdRequest;
import com.homeFinance.homeFinance.dto.request.LoginRequest;
import com.homeFinance.homeFinance.dto.request.RegisterRequest;
import com.homeFinance.homeFinance.dto.request.RegisterWithInviteRequest;
import com.homeFinance.homeFinance.dto.request.UserRequest;
import com.homeFinance.homeFinance.dto.response.AuthResponse;
import com.homeFinance.homeFinance.dto.response.HouseholdResponse;
import com.homeFinance.homeFinance.dto.response.UserResponse;
import com.homeFinance.homeFinance.entity.Invitation;
import com.homeFinance.homeFinance.entity.User;
import com.homeFinance.homeFinance.entity.UserBalance;
import com.homeFinance.homeFinance.mapper.UserMapper;
import com.homeFinance.homeFinance.repository.InvitationRepository;
import com.homeFinance.homeFinance.repository.PeriodRepository;
import com.homeFinance.homeFinance.repository.UserBalanceRepository;
import com.homeFinance.homeFinance.repository.UserRepository;
import com.homeFinance.homeFinance.service.AuthService;
import com.homeFinance.homeFinance.service.HouseholdService;
import com.homeFinance.homeFinance.service.JwtService;
import com.homeFinance.homeFinance.service.UserService;

/**
 * AuthServiceImpl
 */
@Service
public class AuthServiceImpl implements AuthService {

  private final UserService userService;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final HouseholdService householdService;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final InvitationRepository invitationRepository;
  private final PeriodRepository periodRepository;
  private final UserBalanceRepository userBalanceRepository;

  public AuthServiceImpl(UserService userService, HouseholdService householdService, JwtService jwtService,
      UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper,
      InvitationRepository invitationRepository, PeriodRepository periodRepository,
      UserBalanceRepository userBalanceRepository) {
    this.householdService = householdService;
    this.userService = userService;
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
    this.invitationRepository = invitationRepository;
    this.periodRepository = periodRepository;
    this.userBalanceRepository = userBalanceRepository;

  }

  @Override
  public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }

    UserResponse userResponse = userMapper.toResponse(user);
    String token = jwtService.generateToken(userResponse);

    return new AuthResponse(token, user.getId(), user.getName(), user.getHousehold().getId());
  }

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest request) {
    HouseholdResponse houseHold = householdService.create(new HouseholdRequest(request.householdName()));
    UserResponse user = userService.create(
        new UserRequest(request.userName(), request.email(), request.password(), houseHold.id()));
    String token = jwtService.generateToken(user);
    return new AuthResponse(token, user.id(), user.name(), user.household().id());
  }

  @Override
  @Transactional
  public AuthResponse registerWithInvitation(RegisterWithInviteRequest request) {
    Invitation inv = invitationRepository.findByCode(request.inviteCode())
        .orElseThrow(() -> new BadCredentialsException("Invalid code"));

    if (inv.getExpiresAt().isBefore(LocalDateTime.now())) {
      throw new BadCredentialsException("Invitation expired");
    }
    if (!inv.isActive()) {
      throw new BadCredentialsException("Invitation is no longer active");
    }

    UUID householdId = inv.getHousehold().getId();

    UserResponse userResponse = userService.create(
        new UserRequest(request.userName(), request.email(), request.password(), householdId));

    periodRepository.findTopByHouseholdIdOrderByMonthDesc(householdId)
        .filter(period -> !period.getIsClosed())
        .ifPresent(activePeriod -> {
          User userEntity = userRepository.getReferenceById(userResponse.id());
          UserBalance balance = new UserBalance();
          balance.setUser(userEntity);
          balance.setPeriod(activePeriod);
          userBalanceRepository.save(balance);
        });

    inv.setActive(false);
    invitationRepository.save(inv);

    String token = jwtService.generateToken(userResponse);
    return new AuthResponse(token, userResponse.id(), userResponse.name(), householdId);
  }
}
