package by.barsuk.goods.controller;

import by.barsuk.goods.model.Manufacturer;
import by.barsuk.goods.service.GoodsService;
import by.barsuk.goods.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;
    private final GoodsService goodsService;

    public ManufacturerController(ManufacturerService manufacturerService, GoodsService goodsService) {
        this.manufacturerService = manufacturerService;
        this.goodsService = goodsService;
    }

    @GetMapping("/all")
    public String getAllManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return "manufacturers/all";
    }

    @GetMapping("/view")
    public String viewManufacturer(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findById(id)
                .orElse(null);
        if (manufacturer == null) {
            return "redirect:all";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/view";
    }

    @GetMapping("/add")
    public String getAddManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturers/add";
    }

    @PostMapping("/add")
    public String addManufacturer(@Valid @ModelAttribute("manufacturer") Manufacturer newManufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manufacturers/add";
        }
        manufacturerService.save(newManufacturer);
        model.addAttribute("manufacturerMessage", "Производитель был успешно добавлен!");
        return viewManufacturer(newManufacturer.getId(), model);
    }

    @GetMapping("/edit")
    public String getEditManufacturerForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findById(id)
                .orElse(null);
        if (manufacturer == null) {
            return "redirect:all";
        }
        model.addAttribute("manufacturer", manufacturer);
        return "manufacturers/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "manufacturers/edit";
        }
        if (manufacturerService.save(manufacturer) != null) {
            model.addAttribute("manufacturerMessage", "Производитель был успешно отредактирован!");
            return viewManufacturer(manufacturer.getId(), model);
        }
        model.addAttribute("manufacturerMessage", "Что-то пошло не так!");
        return "manufacturers/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Manufacturer manufacturer = manufacturerService.findById(id)
                .orElse(null);
        if (manufacturer == null) {
            return "redirect:all";
        }
        if (!goodsService.findGoodsByManufacturerId(id)
                .isEmpty()) {
            model.addAttribute("manufacturerMessage", "Производитель не может быть удалён, так как в базе есть украшения этого производителя!");
            return viewManufacturer(id, model);
        }
        manufacturerService.deleteById(id);
        model.addAttribute("manufacturerMessage", "Производитель был успешно удалён!");
        return getAllManufacturers(model);
    }
}
