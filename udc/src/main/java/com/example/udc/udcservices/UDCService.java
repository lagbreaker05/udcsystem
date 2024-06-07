package com.example.udc.udcservices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.udc.model.UDC;

@Service
public class UDCService {
	  // Перевірка наявність дочірніх елементів
    public boolean udcHasChildren(UDC udc) {
        return udc.getChildren() != null && !udc.getChildren().isEmpty();
    }

    // Отримання дочірніх елементів
    public List<UDC> udcChildren(UDC udc) {
        return udc.getChildren();
    }
}
