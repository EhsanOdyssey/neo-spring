package neo.ehsanodyssey.java.spring.controller;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import neo.ehsanodyssey.java.spring.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import neo.ehsanodyssey.java.spring.entities.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

    @Autowired
    private ResourceService service;

    // Stream data
    @RequestMapping("/rbe")
    public ResponseBodyEmitter handleRBE() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < 500; i++) {
                try {
                    emitter.send(String.valueOf(i));
                    emitter.send(" - ", MediaType.TEXT_PLAIN);
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                }
            }
            emitter.complete();
        });
        return emitter;
    }

    // SseEmitter used for push message as async response to browser by streaming data
    @RequestMapping("/pricing/sse")
    public SseEmitter getPricingSSE() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    emitter.send(new Random().nextInt(10)+1);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                }
            }
            emitter.complete();
        });
        return emitter;
    }

    // Return Async response with Callable Object
    @RequestMapping("/pricing/callableobject")
    @ResponseBody
    public Callable<String> getPricingByCallableObject() {

        System.out.println("getPricing invoked...");

        System.out.println("getPricing finished...");
        return () -> {
                System.out.println("callable invoked...");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return String.valueOf(new Random().nextInt(10)+1);
        };
//        return new Callable<String>() {
//
//            @Override
//            public String call() throws Exception {
//
//                System.out.println("callable invoked...");
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                return String.valueOf(new Random().nextInt(10)+1);
//            }
//        };
    }

    // Return Async response with Deferred Result
    @RequestMapping("/pricing/deferredresult")
    @ResponseBody
    public DeferredResult<String> getPricingByDeferredResult() {

        System.out.println("getPricing invoked...");
        DeferredResult<String> deferredResult = new DeferredResult<>();

        new Thread(()-> {
            System.out.println("runnable invoked...");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("returning runnable result...");
            deferredResult.setResult(String.valueOf(new Random().nextInt(10) + 1));
        }).start();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println("runnable invoked...");
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("returning result...");
//                deferredResult.setResult(String.valueOf(new Random().nextInt(10) + 1));
//            }
//
//        }).start();

        System.out.println("getPricing finished...");
        return deferredResult;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            return "The file size is: " + file.getSize();
        } else {
            return "The file is empty";
        }
    }

    @RequestMapping("/{resourceId}")
    @ResponseBody
    public Resource findResource(@PathVariable("resourceId") Resource resource) {
        return resource;
    }

    @RequestMapping("/find")
    public String find(Model model) {
        model.addAttribute("resources", service.findAll());
        return "resources";
    }

    @ResponseBody
    @RequestMapping("/request")
    public String request(@RequestBody String resource) {
        System.out.println(resource);
        //Send out an email
        return "The request has been sent for approval";
    }

    @RequestMapping("/review")
    public String review(@ModelAttribute Resource resource) {
        return "resource_review";
    }

    @RequestMapping("/add")
    public String add() {
        System.out.println("Invoking the add method");
        return "resource_add";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute Resource resource, SessionStatus status) {
        System.out.println(resource);
        System.out.println("Invoking the save method");
        status.setComplete(); // Reset session values
        return "redirect:/resource/add";
    }

    @ModelAttribute("resource")
    public Resource getResource() {
        System.out.println("Adding an attribute to the model");
        return new Resource();
    }

    @ModelAttribute(value = "checkOptions")
    public List<String> getChecks() {
        return new LinkedList<>(Arrays.asList(new String[]{"Lead Time", "Special Rate", "Requires Approval"}));
    }

    @ModelAttribute(value = "radioOptions")
    public List<String> getRadios() {
        return new LinkedList<>(Arrays.asList(new String[]{"Hours", "Piece", "Tons"}));
    }

    @ModelAttribute(value = "typeOptions")
    public List<String> getTypes() {
        return new LinkedList<>(
                Arrays.asList(new String[]{"Material", "Staff", "Other", "Equipment"}));
    }
}
