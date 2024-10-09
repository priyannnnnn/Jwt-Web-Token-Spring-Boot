package com.supricode.security.interfacee;

import com.supricode.security.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportInterface extends JpaRepository<Report, Long> {
}
