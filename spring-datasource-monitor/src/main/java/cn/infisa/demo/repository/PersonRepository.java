package cn.infisa.demo.repository;

import cn.infisa.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@Transactional
public interface PersonRepository extends PagingAndSortingRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    List<Person> findByNameStartsWith(@Param("name") String name);


    /**
     * delete url 无法暴露出去
     */
//    @RestResource(path = "deleteByName", rel = "deleteByName")
//    List<Person> deleteByName(@Param("name") String name);
}
