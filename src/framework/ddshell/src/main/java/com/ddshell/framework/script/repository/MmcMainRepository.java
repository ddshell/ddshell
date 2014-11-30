package com.ddshell.framework.script.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddshell.framework.script.entity.MmcMain;

public interface MmcMainRepository extends JpaRepository<MmcMain, String> {
}
