package pckg;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

class Foo {
  public Date getPaginationDate() {
    return null;
  }
}

public class Reproducer {
  // Add some comment
  public <T extends Foo> void foo(List<T> subList, List<T> completeListOfElements) {
    final Optional<T> lastElement = subList
      .stream()
      .min(Comparator.comparing(T::getPaginationDate));
    Date paginationLastTimestamp = null;
    if (lastElement.isPresent()) {
      final T lastFilteredElement = subList.get(subList.size() - 1);
      final int indexOfLastElement = completeListOfElements.size() - 1;
      if (completeListOfElements.indexOf(lastFilteredElement) < indexOfLastElement) {
        paginationLastTimestamp = lastElement
          .get()
          .getPaginationDate();
      }
    }
  }
}
