package mybatis.controller

import spock.lang.Specification

class AssertUtils extends Specification {

    protected <T, E> void assertItems(List<T> expected, List<E> items, Closure assertion) {
        assert expected.size() == items.size()

        expected.eachWithIndex { entry, index ->
            assertion(entry, items.get(index))
        }
    }
}
