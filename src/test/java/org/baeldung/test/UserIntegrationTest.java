package org.baeldung.test;

import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.dao.VerificationTokenRepository;
import org.baeldung.persistence.model.User;
import org.baeldung.persistence.model.VerificationToken;
import org.baeldung.persistence.service.IUserService;
import org.baeldung.spring.TestDBConfig;
import org.baeldung.validation.EmailExistsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestDBConfig.class }, loader = AnnotationConfigContextLoader.class)
@Transactional
public class UserIntegrationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    IUserService userService;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    Long token_id;
    Long user_id;

    @Before
    public void givenUserAndVerificationToken() throws EmailExistsException {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("SecretPassword");
        user.setFirstName("First");
        user.setLastName("Last");
        entityManager.persist(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        entityManager.persist(verificationToken);

        entityManager.flush();
        entityManager.clear();

        token_id = verificationToken.getId();
        user_id = user.getId();
    }

    @After
    public void flushAfter() {
        entityManager.flush();
    }

    @Test
    public void whenContextLoad_thenCorrect() {
        assertEquals(1, userRepository.count());
        assertEquals(1, tokenRepository.count());
    }

    @Test
    public void whenRemovingUser_thenFKViolationException() {
        thrown.expect(PersistenceException.class);
        userRepository.delete(user_id);
    }

    @Test
    public void whenRemovingTokenThenUser_thenCorrect() {
        tokenRepository.delete(token_id);
        userRepository.delete(user_id);
    }
}

