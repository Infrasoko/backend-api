package com.laurent.infrasoko.common.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
interface DocumentFileRepository extends JpaRepository<DocumentFile, Long> {
    boolean existsById(Long id);
    List<DocumentFile> findByIdIn(Set<Long> id);
}