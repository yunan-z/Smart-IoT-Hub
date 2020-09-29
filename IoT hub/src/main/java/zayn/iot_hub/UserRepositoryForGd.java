package zayn.iot_hub;

import zayn.iot_hub.GroupData;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Modifying;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.transaction.annotation.Transactional;


    @Transactional
    public interface UserRepositoryForGd extends JpaRepository<GroupData, Long> {
        @Modifying
        @Query(value="update groupName set member = :member where id = :id",nativeQuery = true)
        void updateNameById(@Param("id") Long id,@Param("member") List<String> member);
    } 
