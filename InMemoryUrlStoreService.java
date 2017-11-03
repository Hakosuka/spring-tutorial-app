/**
 * Tells Spring that this class is a "bean" from the Service layer that can be
 * injected to other beans (e.g. UrlController)
 */
@Service
public class InMemoryUrlStoreService implements IUrlStoreService{
  private Map<String, String> urlByIdMap = new ConcurrentHashmap<>();

  @Override
  public String findUrlById(String id) {
    return urlByIdMap.get(id);
  }

  @Override
  public void storeUrl(String id, String url) {
    urlByIdMap.put(id, url);
  }
}
