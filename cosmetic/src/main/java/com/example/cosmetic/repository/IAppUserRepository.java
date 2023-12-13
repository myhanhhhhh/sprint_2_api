package com.example.cosmetic.repository;

import com.example.cosmetic.model.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IAppUserRepository extends JpaRepository<AppUser, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM app_user WHERE " +
            "user_name = :name and is_deleted = 0", nativeQuery = true)
    AppUser findAppUserByName(@Param("name") String userName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO app_user(email, user_name, `password`," +
            "is_deleted,is_online) " +
            "VALUES (:#{#appUser.email}, " +
            ":#{#appUser.userName}, :#{#appUser.password},0,0) ", nativeQuery = true)
    Integer createNewAppUser(AppUser appUser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE app_user set is_online = 1 WHERE id = :#{#appUser.id}", nativeQuery = true)
    Integer updateAppUserIsOnline(AppUser appUser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE app_user set is_online = 0 WHERE user_name = :userName", nativeQuery = true)
    Integer updateAppUserIsOffline(@Param("userName") String userName);

    @Query(value = "SELECT au.id from app_user au WHERE au.user_name = :userName", nativeQuery = true)
    Integer findIdByUserName(@Param("userName") String userName);

    @Query(value = " select r.id from app_role r where r.name = :name ", nativeQuery = true)
    Integer findAppRoleIdByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_role (app_role_id,app_user_id) VALUES (:roleId, :id)", nativeQuery = true)
    void insertRoleForCustomer(Integer roleId, Integer id);

}
