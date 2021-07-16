package com.street.cat.cat.controller;

import com.street.cat.cat.dto.CatReqDTO;
import com.street.cat.cat.dto.CatResDTO;
import com.street.cat.cat.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cats")
public class CatController {

    private final CatService catService;

    @PostMapping("")
    public ResponseEntity<String> createCat(@RequestBody CatReqDTO catReqDTO){
        return catService.createCat(catReqDTO);
    }

    @GetMapping("/{catId}/info/")
    public ResponseEntity<CatResDTO> retrieveCat(@PathVariable("catId") String catId) {
        return catService.retrieveCat(catId);
    }

    @GetMapping("/{catId}/points/")
    public ResponseEntity<Set<String>> retrieveCatPoint(@PathVariable("catId") String catId) {
        return catService.retrieveCatPoint(catId);
    }


}
