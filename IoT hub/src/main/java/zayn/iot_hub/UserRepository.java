package zayn.iot_hub;

import zayn.iot_hub.PlugData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface UserRepository extends JpaRepository<PlugData, Long> {
    @Modifying
    @Query(value="update test set plug = :name where id = :id",nativeQuery = true)
    void updateNameById(@Param("id") Long id,@Param("name") String name);
}