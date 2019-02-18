import spock.lang.Specification

class SimpleTestSpecification extends Specification {

    def "测试" () {
        expect:
        1+1==2
    }
}
