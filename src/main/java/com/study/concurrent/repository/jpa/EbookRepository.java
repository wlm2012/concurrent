package com.study.concurrent.repository.jpa;

import com.study.concurrent.repository.po.EbookPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EbookRepository extends JpaRepository<EbookPO, Long> {

    @Query(value = """
            select e from EbookPO e where
            (e.id in :ids or :ids is null)""")
    List<EbookPO> findByIds(List<Long> ids);
}
