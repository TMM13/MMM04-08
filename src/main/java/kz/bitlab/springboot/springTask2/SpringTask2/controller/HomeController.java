package kz.bitlab.springboot.springTask2.SpringTask2.controller;

import kz.bitlab.springboot.springTask2.SpringTask2.model.RequestModel;
import kz.bitlab.springboot.springTask2.SpringTask2.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(value = "/")
    public String reqPage(Model model){
        List<RequestModel>requestModelList = requestRepository.findAll();
        model.addAttribute("calls",requestModelList);
        return "req";
    }

    @GetMapping(value = "/add")
    public String addRequestPage(Model model){
        return "adding";
    }

    @GetMapping(value = "/details/{requestId}")
    public String requestDetails(@PathVariable(name = "requestId") Long id, Model model){
        RequestModel request = requestRepository.findById(id).orElse(null);
        model.addAttribute("call", request);
        return "details";
    }

    @GetMapping(value = "/new")
    public String newRequest(Model model){
        List<RequestModel> requestModelList = requestRepository.findAllByHandledIsFalse();
        model.addAttribute("calls",requestModelList);
        return "req";
    }

    @GetMapping(value = "/seen")
    public String seenRequest(Model model){
        List<RequestModel> requestModelList = requestRepository.findAllByHandledIsTrue();
        model.addAttribute("calls",requestModelList);
        return "req";
    }

    @PostMapping(value = "/add")
    public String addRequest(RequestModel request){
        requestRepository.save(request);
        return "redirect:/";
    }

    @PostMapping(value = "/save")
    public String saveRequest(RequestModel request){
        request.setHandled(true);
        requestRepository.save(request);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String deleteRequest(@RequestParam(name = "id") Long id){
        requestRepository.deleteById(id);
        return "redirect:/";
    }
}
