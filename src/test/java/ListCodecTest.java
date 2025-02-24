import com.google.gson.JsonArray;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import org.junit.jupiter.api.Test;

import java.util.List;

import static utilitycodecs.util.codec.mixedtuple.Intermediary.intermediary;
import static utilitycodecs.util.codec.mixedtuple.Intermediary.terminalIntermediary;
import static utilitycodecs.util.codec.mixedtuple.Primary.primary;
import static utilitycodecs.util.codec.mixedtuple.SequenceIntermediary.sequence;
import static utilitycodecs.util.codec.mixedtuple.VariadicIntermediary.terminalVariadic;

public class ListCodecTest {
    record TestRecord1(List<Integer> twoNumbers, List<String> s) {
        public TestRecord1 {
            if (twoNumbers.size() != 2) {
                throw new IllegalArgumentException("twoNumbers must have exactly 2 elements");
            }
        }

        public static final Codec<TestRecord1> CODEC = primary(
                sequence(
                        terminalVariadic(
                                Codec.STRING.listOf(),
                                TestRecord1::s
                        ),
                        Codec.INT.listOf(),
                        TestRecord1::twoNumbers,
                        2
                ),
                nums -> strs ->
                        new TestRecord1(nums, strs)
        );
    }

    record TestRecord2(String s, int q, int i, List<Integer> others) {
        public static final Codec<TestRecord2> CODEC = primary(
                intermediary(
                        intermediary(
                                intermediary(
                                        terminalVariadic(
                                                Codec.INT.listOf(),
                                                TestRecord2::others
                                        ),
                                        Codec.INT,
                                        TestRecord2::i
                                ),
                                Codec.INT,
                                TestRecord2::q
                        ),
                        Codec.STRING,
                        TestRecord2::s
                ),
                s -> q -> i -> others ->
                        new TestRecord2(s, q, i, others)
        );
    }

    record TestRecord3(String s, int i) {
        public static final Codec<TestRecord3> CODEC = primary(
                intermediary(
                        terminalIntermediary(
                                Codec.INT,
                                TestRecord3::i
                        ),
                        Codec.STRING,
                        TestRecord3::s
                ),
                s -> i ->
                        new TestRecord3(s, i)
        );
    }

    @Test
    public void listTest() {
        var record = new TestRecord1(List.of(1, 2), List.of("test", "test2"));
        var encoded = TestRecord1.CODEC.encodeStart(JsonOps.INSTANCE, record).getOrThrow();
        TestRecord1.CODEC.decode(JsonOps.INSTANCE, encoded).getOrThrow();
    }

    @Test
    public void listTest2() {
        var record = new TestRecord2("test", 1, 2, List.of(3, 4));
        var encoded = TestRecord2.CODEC.encodeStart(JsonOps.INSTANCE, record).getOrThrow();
        TestRecord2.CODEC.decode(JsonOps.INSTANCE, encoded).getOrThrow();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("test");
        for (int i = 0; i < 30; i++) {
            jsonArray.add(i);
        }
        TestRecord2.CODEC.decode(JsonOps.INSTANCE, jsonArray).getOrThrow();
    }

    @Test
    public void listTest3() {
        var record = new TestRecord3("test", 1);
        var encoded = TestRecord3.CODEC.encodeStart(JsonOps.INSTANCE, record).getOrThrow();
        TestRecord3.CODEC.decode(JsonOps.INSTANCE, encoded).getOrThrow();
    }
}
