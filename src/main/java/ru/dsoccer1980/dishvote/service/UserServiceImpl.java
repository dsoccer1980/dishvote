package ru.dsoccer1980.dishvote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.dsoccer1980.dishvote.model.User;
import ru.dsoccer1980.dishvote.repository.UserRepository;
import ru.dsoccer1980.dishvote.util.Exception.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.dsoccer1980.dishvote.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User get(int id) throws NotFoundException {
        log.info("get user with (Id) :  {} ", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(User user) throws NotFoundException {
        log.info("update user:  {} ", user);
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public List<User> getAll() {
        log.info("get all users");
        return repository.getAll();
    }

    @Override
    public User create(User user) {
        log.info("create user:  {} ", user);
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        log.info("delete user with (Id) :  {} ", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        org.springframework.security.core.userdetails.User grantedUser = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);

        if (!user.isNew()) {
            ru.dsoccer1980.dishvote.web.AuthorizedUser.setId(user.getId());
        }
        return grantedUser;

    }
}