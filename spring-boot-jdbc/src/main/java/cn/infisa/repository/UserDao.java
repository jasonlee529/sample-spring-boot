package cn.infisa.repository;

import cn.infisa.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserDao extends JpaSpecificationExecutor<User>,PagingAndSortingRepository<User,Long>{
}
