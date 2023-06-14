package kz.bitlab.springboot.springTask2.SpringTask2.repository;

import kz.bitlab.springboot.springTask2.SpringTask2.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestModel, Long> {
    List<RequestModel> findAllByHandledIsTrue();
    List<RequestModel> findAllByHandledIsFalse();
}
