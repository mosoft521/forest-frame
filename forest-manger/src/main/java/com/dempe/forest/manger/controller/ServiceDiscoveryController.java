package com.dempe.forest.manger.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/17
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("discovery")
public class ServiceDiscoveryController {

//    @Resource
//    private ForestNameService nameService;
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping("index")
//    public String index(Model model) throws Exception {
//        List<Collection<ServiceInstance<NodeDetails>>> list = nameService.list();
//        List<NodeDetails> nodeList = Lists.newArrayList();
//        for (Collection<ServiceInstance<NodeDetails>> serviceInstances : list) {
//            for (ServiceInstance<NodeDetails> serviceInstance : serviceInstances) {
//                NodeDetails nodeDetails = new NodeDetails();
//                nodeDetails.setIp(serviceInstance.getAddress());
//                nodeDetails.setPort(serviceInstance.getPort());
//                nodeDetails.setName(serviceInstance.getName());
//                nodeList.add(nodeDetails);
//            }
//        }
//        model.addAttribute("nodeList", nodeList);
//        return "/discovery/index";
//    }

}
