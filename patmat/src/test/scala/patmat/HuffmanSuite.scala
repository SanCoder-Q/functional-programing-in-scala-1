package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t4 = makeCodeTree(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5),
      Leaf('d', 4))
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }


  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }


  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }


  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode very short codes should work") {
    new TestTrees {
      assert(decode(t1, List(0, 1)) === "ab".toList)
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("encode text should be optimal") {
    assert(encode(createCodeTree("sometext".toList))("sometext".toList).size === 20)
    assert(encode(createCodeTree("AIR STRIKES ALONE".toList))("AIR STRIKES ALONE".toList).size === 58)
    assert(encode(createCodeTree("IN YOU IVE FOUND".toList))("IN YOU IVE FOUND".toList).size === 52)
  }

  test("CodeTable should created correctly") {
    new TestTrees {
      val table = convert(t4)
      assert(table == List(('a', List(0, 0)),
        ('b', List(0, 1)),
        ('d', List(1))))
    }
  }

  test("quick encode ginves correct byte sequence") {
    new TestTrees {
      val table = quickEncode(frenchCode)_
      val xl = table("charl".toList)
      val xt = table("chart".toList)

      assert(xl == List(1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1))
      assert(xt == List(1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1))
    }
  }

}
