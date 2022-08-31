package by.barsuk.goods.controller;

import by.barsuk.goods.model.Goods;
import by.barsuk.goods.service.CategoryService;
import by.barsuk.goods.service.GoodsService;
import by.barsuk.goods.service.ManufacturerService;
import by.barsuk.goods.service.TypeMetalService;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Base64;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final ManufacturerService manufacturerService;
    private final CategoryService categoryService;
    private final TypeMetalService typeMetalService;

    public GoodsController(GoodsService goodsService, ManufacturerService manufacturerService,
                           CategoryService categoryService, TypeMetalService typeMetalService) {
        this.goodsService = goodsService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
        this.typeMetalService = typeMetalService;
    }

    @GetMapping("/all")
    public String getAllGoods(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findAll());
        return "goods/all";
    }

    @GetMapping("/bracelets")
    public String getAllBracelets(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByCategoryId(3L));
        return "goods/bracelets";
    }

    @GetMapping("/necklaces")
    public String getAllNecklaces(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByCategoryId(4L));
        return "goods/necklaces";
    }

    @GetMapping("/rings")
    public String getAllRings(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByCategoryId(1L));
        return "goods/rings";
    }

    @GetMapping("/earrings")
    public String getAllEarrings(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByCategoryId(2L));
        return "goods/earrings";
    }

    @GetMapping("/silver")
    public String getAllSilver(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByMetalId(1L));
        return "goods/silver";
    }

    @GetMapping("/white")
    public String getAllWhite(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByMetalId(2L));
        return "goods/white";
    }

    @GetMapping("/pink")
    public String getAllPink(Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.findGoodsByMetalId(3L));
        return "goods/pink";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "s", defaultValue = "") String search, Model model) {
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goodsService.searchByName(search));
        return "goods/search";
    }

    @GetMapping("/view")
    public String viewGoods(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Goods goods = goodsService.findById(id)
                .orElse(null);
        if (goods == null) {
            return "redirect:all";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goods);
        return "goods/view";
    }

    @GetMapping("/add")
    public String getAddGoodsForm(Model model) {
        model.addAttribute("goods", new Goods());
        addListsToModel(model);
        return "goods/add";
    }

    @PostMapping("/add")
    public String addGoods(@Valid @ModelAttribute("goods") Goods newGoods, BindingResult result, @RequestParam MultipartFile file, Model model) {
        if (result.hasErrors()) {
            addListsToModel(model);
            return "goods/add";
        }
        try {
            newGoods.setImage(file.getBytes());
        } catch (Exception e) {
            addListsToModel(model);
            model.addAttribute("goodsMessage", "Файл не подходит!");
            return "goods/add";
        }
        goodsService.save(newGoods);
        model.addAttribute("goodsMessage", "Украшение было успешно добавлено!");
        return viewGoods(newGoods.getId(), model);
    }

    @GetMapping("/edit")
    public String getEditGoodsForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Goods goods = goodsService.findById(id)
                .orElse(null);
        if (goods == null) {
            return "redirect:all";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("goods", goods);
        addListsToModel(model);
        return "goods/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("goods") Goods goods, BindingResult result, @RequestParam MultipartFile file, Model model) {
        if (result.hasErrors()) {
            addListsToModel(model);
            model.addAttribute("imgUtil", new ImageUtil());
            return "goods/edit";
        }
        try {
            if (!file.isEmpty()) {
                goods.setImage(file.getBytes());
            }
        } catch (Exception e) {
            addListsToModel(model);
            model.addAttribute("imgUtil", new ImageUtil());
            model.addAttribute("goodsMessage", "Файл не подходит!");
            return "goods/edit";
        }
        if (goodsService.save(goods) != null) {
            model.addAttribute("goodsMessage", "Украшение было успешно отредактировано!");
            return viewGoods(goods.getId(), model);
        }
        model.addAttribute("goodsMessage", "Что-то пошло не так!");
        return "goods/edit";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Goods goods = goodsService.findById(id)
                .orElse(null);
        if (goods == null) {
            return "redirect:all";
        }
        goodsService.deleteById(id);
        model.addAttribute("goodsMessage", "Украшение было успешно удалено!");
        return getAllGoods(model);
    }

    @GetMapping("/order")
    public String orderForm(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        Goods goods = goodsService.findById(id)
                .orElse(null);
        if (goods == null) {
            return "redirect:all";
        }
        model.addAttribute("goods", goods);
        return "goods/order";
    }

    @PostMapping("/order")
    public String order(@ModelAttribute("goods") Goods goods, Model model) {
        model.addAttribute("goodsMessage", "Ваш заказ принят! В скором времени наш менеджер с Вами свяжется.");
        return viewGoods(goods.getId(), model);
    }

    private void addListsToModel(Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", typeMetalService.findAll());
    }

    static class ImageUtil {

        public String getImgData(byte[] byteData) {
            if (byteData == null) {
                return "";
            }
            return Base64.getMimeEncoder()
                    .encodeToString(byteData);
        }
    }
}
