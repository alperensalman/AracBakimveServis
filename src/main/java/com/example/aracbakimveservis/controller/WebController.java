package com.example.aracbakimveservis.controller;

import com.example.aracbakimveservis.dto.ServiceRecordCreateDTO;
import com.example.aracbakimveservis.dto.VehicleCreateDTO;
import com.example.aracbakimveservis.service.ServiceRecordService;
import com.example.aracbakimveservis.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui")
public class WebController {

    private final VehicleService vehicleService;
    private final ServiceRecordService serviceRecordService;

    public WebController(VehicleService vehicleService, ServiceRecordService serviceRecordService) {
        this.vehicleService = vehicleService;
        this.serviceRecordService = serviceRecordService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("totalVehicles", vehicleService.getAllVehicles().size());
        return "dashboard";
    }

    @GetMapping("/vehicles")
    public String vehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("vehicleDTO", new VehicleCreateDTO());
        return "vehicles";
    }

    @PostMapping("/vehicles/add")
    public String addVehicle(@Valid @ModelAttribute("vehicleDTO") VehicleCreateDTO dto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            return "vehicles";
        }
        vehicleService.createVehicle(dto);
        return "redirect:/ui/vehicles";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        model.addAttribute("serviceDTO", new ServiceRecordCreateDTO());
        return "services";
    }

    @PostMapping("/services/add")
    public String addService(@Valid @ModelAttribute("serviceDTO") ServiceRecordCreateDTO dto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicles", vehicleService.getAllVehicles());
            return "services";
        }
        serviceRecordService.createServiceRecord(dto);
        return "redirect:/ui/services";
    }

    @GetMapping("/services/vehicle/{vehicleId}")
    public String servicesByVehicle(@PathVariable Long vehicleId, Model model) {
        model.addAttribute("records", serviceRecordService.getRecordsByVehicle(vehicleId));
        model.addAttribute("vehicleId", vehicleId);
        return "service-records";
    }
}
