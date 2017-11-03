/**
 * The app will follow the MVC design pattern, so this class will serve as the
 * "Controller".
 */
@Controller
public class UrlController {
  @Autowired
  private IUrlStoreService urlStoreService;

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String showForm(ShortenUrlRequest request) {
    return "shortener";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public void redirectToUrl(@PathVariable String id, HttpServletResponse resp)
    throws Exception {
      final String url = urlStoreService.findUrlById(id);
      if(url!=null) {
        resp.addHeader("Location", url);
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
      }
      else {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      }
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView shortenUrl(HttpServletRequest httpRequest,
      @Valid ShortenUrlRequest request,
      BindingResult bindingResult){
        String url = request.getUrl();
        if(!isUrlValid(url)) {
          bindingResult.addError(new ObjectError("url",
          "Invalid url format: " + url));
        }
        ModelAndView mav = new ModelAndView("shortener");
        if(!bindingResult.hasErrors()){
          final String id = Hashing.murmur3_32()
            .hashString(url, StandardCharsets.UTF_8).toString();
          urlStoreService.storeUrl(id, url);
          String requestUrl = httpRequest.getRequestURL().toString();
          String prefix = requestUrl.substring(0,
            requestUrl.indexOf(httpRequest.getRequestURI(),
              "http://".length()));
          mav.addObject("shortenedUrl", prefix + "/" + id);
        }
        return mav;
      }
}
