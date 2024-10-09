package com.supricode.security.controller;

import com.supricode.security.interfacee.DetailProblemRepository;
import com.supricode.security.model.Detail_Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class DetailProblemController {

    @Autowired
    private DetailProblemRepository detailProblemRepository;

    // POST method to create a new DetailProblem

    @PostMapping("/detail-problems")
    public Detail_Problem createDetailProblem(@RequestBody Detail_Problem detailProblem) {
        return detailProblemRepository.save(detailProblem);
    }

    // GET method to retrieve all DetailProblems
    @GetMapping("/detail-problems")
    public List<Detail_Problem> getAllDetailProblems() {
        return detailProblemRepository.findAll();
    }

    // GET method to retrieve a DetailProblem by ID
    @GetMapping("/{id}")
    public ResponseEntity<Detail_Problem> getDetailProblemById(@PathVariable Long id) {
        Detail_Problem detailProblem = detailProblemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetailProblem not found with id " + id));
        return ResponseEntity.ok(detailProblem);
    }

}
