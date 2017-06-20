package ua.com.alexcoffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.model.Role;
import ua.com.alexcoffee.model.User;
import ua.com.alexcoffee.repository.RoleRepository;
import ua.com.alexcoffee.repository.UserRepository;
import ua.com.alexcoffee.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Класс сервисного слоя реализует методы доступа объектов класса {@link User}
 * в базе данных интерфейса {@link UserService}, наследует родительский
 * класс {@link MainServiceImpl}, в котором реализованы основные методы,
 * а также методы интерфейса {@link UserDetailsService}.
 * Класс помечан аннотацией @Service - аннотация обьявляющая, что этот класс представляет
 * собой сервис – компонент сервис-слоя. Сервис является подтипом класса @Component.
 * Использование данной аннотации позволит искать бины-сервисы автоматически.
 * Методы класса помечены аннотацией @Transactional - перед исполнением метода помеченного
 * данной аннотацией начинается транзакция, после выполнения метода транзакция коммитится,
 * при выбрасывании RuntimeException откатывается.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.2
 * @see MainServiceImpl
 * @see UserService
 * @see User
 * @see UserRepository
 */
@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class UserServiceImpl extends MainServiceImpl<User> implements UserService, UserDetailsService {

    /**
     * ID роли клиента в базе данных.
     */
    private final static Long CLIENT_ROLE_ID = 1L;

    /**
     * ID роли адмиистратора в базе данных.
     */
    private final static Long ADMIN_ROLE_ID = 2L;

    /**
     * ID роли менеджера в базе данных.
     */
    private final static Long MANAGER_ROLE_ID = 3L;

    /**
     * Реализация интерфейса {@link UserRepository}
     * для работы пользователей с базой данных.
     */
    private final UserRepository repository;

    /**
     * Реализация репозитория {@link RoleRepository} для работы
     * ролями пользователей с базой данных.
     */
    private final RoleRepository roleRepository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository     Реализация интерфейса {@link UserRepository}
     *                       для работы пользователей с базой данных.
     * @param roleRepository Реализация репозитория {@link RoleRepository}
     *                       для работы ролями пользователей с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public UserServiceImpl(
            final UserRepository repository,
            final RoleRepository roleRepository
    ) {
        super(repository);
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    /**
     * Возвращает пользователя, у которого совпадает имя с
     * значением входящего параметра. Режим только для чтения.
     *
     * @param name Имя пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с именем name.
     * @throws WrongInformationException Бросает исключение,
     *                                   когда пустой входной параметр name.
     * @throws BadRequestException       Бросает исключение,
     *                                   если не найден пользователь с входящим параметром name.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByName(final String name) throws WrongInformationException, BadRequestException {
        if (isBlank(name)) {
            throw new WrongInformationException("No user name!");
        }
        final User user = this.repository.findByName(name);
        if (user == null) {
            throw new BadRequestException("Can't find user by name " + name + "!");
        }
        return user;
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     *
     * @param username Логин пользователя для возврата.
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр username.
     * @throws BadRequestException       Бросает исключение,
     *                                   если не найден пользователь с входящим параметром username.
     */
    @Override
    @Transactional(readOnly = true)
    public User getByUsername(final String username)
            throws WrongInformationException, BadRequestException {
        if (isBlank(username)) {
            throw new WrongInformationException("No username!");
        }
        final User user = this.repository.findByUsername(username);
        if (user == null) {
            throw new BadRequestException("Can't find user by username " + username + "!");
        }
        return user;
    }

    /**
     * Возвращает главного администратора сайта.
     * Режим только для чтения.
     *
     * @return Объект класса {@link User} - главный администратор.
     * @throws BadRequestException Бросает исключение,
     *                             если не найден пользователь-админ.
     */
    @Override
    @Transactional(readOnly = true)
    public User getMainAdministrator() throws BadRequestException {
        final Role adminRole = this.roleRepository.findOne(ADMIN_ROLE_ID);
        final User user = this.repository.findAllByRole(adminRole).get(0);
        if (user == null) {
            throw new BadRequestException("Can't find administrator!");
        }
        return user;
    }

    /**
     * Возвращает список всех администраторов сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список администраторов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAdministrators() {
        final Role adminRole = this.roleRepository.findOne(ADMIN_ROLE_ID);
        return this.repository.findAllByRole(adminRole);
    }

    /**
     * Возвращает список всех менеджеров сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список менеджеров.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getManagers() {
        final Role managerRole = this.roleRepository.findOne(MANAGER_ROLE_ID);
        return this.repository.findAllByRole(managerRole);
    }

    /**
     * Возвращает список всех клиентов сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список клиентов.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getClients() {
        final Role clientRole = this.roleRepository.findOne(CLIENT_ROLE_ID);
        return this.repository.findAllByRole(clientRole);
    }

    /**
     * Возвращает список персонала сайта.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} -
     * список персонала.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getPersonnel() {
        final List<User> users = new ArrayList<>();
        users.addAll(getAdministrators());
        users.addAll(getManagers());
        return users;
    }

    /**
     * Возвращает авторизированого пользователя.
     * Режим только для чтения.
     *
     * @return Объект класса {@link User} - авторизированый пользователь.
     */
    @Override
    @Transactional(readOnly = true)
    public User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();
        } catch (Exception ex) {
            ex.printStackTrace();
            user = null;
        }
        return user;
    }

    /**
     * Удаляет пользователя, у которого совпадает имя с значением входящего параметра.
     *
     * @param name Имя пользователя для удаления.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр username.
     */
    @Override
    @Transactional
    public void removeByName(final String name) throws WrongInformationException {
        if (isBlank(name)) {
            throw new WrongInformationException("No username!");
        }
        this.repository.deleteByName(name);
    }

    /**
     * Удаляет пользователя из базы даных, у которого совпадает
     * роль с значением входящего параметра.
     *
     * @param role Роль пользователя для удаления.
     * @throws WrongInformationException Бросает исключение,
     *                                   если пустой входной параметр role.
     */
    @Override
    @Transactional
    public void removeByRole(final Role role) throws WrongInformationException {
        if (role == null) {
            throw new WrongInformationException("No user role!");
        }
        this.repository.deleteAllByRole(role);
    }

    /**
     * Удаляет список персонала сайта.
     */
    @Override
    @Transactional
    public void removePersonnel() {
        final List<User> personnel = getPersonnel();
        if (personnel.isEmpty()) {
            return;
        }
        personnel.remove(getMainAdministrator());
        this.repository.delete(personnel);
    }

    /**
     * Возвращает пользователя, у которого совпадает уникальный
     * логин с значением входящего параметра. Режим только для чтения.
     * Реализованый метод интерфейса {@link UserDetailsService}.
     *
     * @param username Логин пользователя для возврата
     * @return Объект класса {@link User} - пользователь с логином username.
     * @throws UsernameNotFoundException Бросает исключеник,
     *                                   если пользователь с логином username не найден.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return getByUsername(username);
    }
}
