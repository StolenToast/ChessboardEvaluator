package seitz;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

public class ChessTest {
	@Test
	public void testReflection() {
		Class<?> iClass = Chess.class;
		Field[] iFields = iClass.getDeclaredFields();

		for (Field f : iFields) {
			if (!f.isSynthetic()) {
				assertTrue("Field \"" + f.getName() + "\" must be private",
						Modifier.isPrivate(f.getModifiers()));
				if (Modifier.isStatic(f.getModifiers())) {
					assertTrue("Field \"" + f.getName() + "\" must be final",
							Modifier.isFinal(f.getModifiers()));
				}
			}
		}
	}

	@Test
	public void test01() {
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = '-';
		Character actual = Chess.getCheck(board);
		assertEquals("No king is in check: board is empty.", expected, actual);
	}

	@Test
	public void test02() {
		char[][] board = { { 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r' },
				{ 'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
				{ 'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R' } };
		Character expected = '-';
		Character actual = Chess.getCheck(board);
		assertEquals("No king is in check.", expected, actual);
	}

	@Test
	public void test03() {
		char[][] board = { 
				{ 'N', '.', 'Q', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', 'k', '.', '.', '.', '.', '.', '.' },
 				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', 'K', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'N';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by knight.", expected, actual);
	}

	@Test
	public void test04() {
		char[][] board = { 
				{ '.', '.', '.', '.', '.', '.', 'R', '.' },
				{ '.', '.', '.', '.', '.', 'N', 'B', 'R' },
				{ '.', '.', '.', '.', 'Q', 'B', 'k', '.' },
				{ '.', '.', '.', '.', '.', 'R', '.', 'P' },
				{ '.', '.', '.', '.', 'K', '.', 'N', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'P';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by pawn.", expected, actual);
	}

	@Test
	public void test05() {
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'B', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'B', '.', '.', '.' },
				{ '.', '.', '.', '.', 'Q', '.', 'N', 'R' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', 'P' },
				{ '.', '.', '.', '.', 'R', 'K', 'p', 'k' } };
		Character expected = 'Q';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by queen.", expected, actual);
	}

	@Test
	public void test06() {
		char[][] board = { 
				{ '.', '.', 'k', '.', '.', '.', '.', '.' },
				{ 'p', 'p', 'p', '.', 'p', 'p', 'p', 'p' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'R', '.', '.', '.', 'B', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P' },
				{ 'K', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'B';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by bishop.", expected, actual);
	}

	@Test
	public void test07() {
		char[][] board = { { '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'r', '.', '.', '.' },
				{ '.', '.', 'q', '.', 'p', '.', 'b', '.' },
				{ '.', '.', '.', 'n', '.', 'p', '.', '.' },
				{ '.', '.', '.', '.', 'K', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'k', '.', '.', '.' } };
		Character expected = 'p';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by pawn.", expected, actual);
	}

	@Test
	public void test08() {
		char[][] board = { { 'r', 'n', 'b', 'q', 'k', '.', 'n', 'r' },
				{ 'p', 'p', 'p', '.', '.', 'p', 'p', 'p' },
				{ '.', '.', '.', '.', 'p', '.', '.', '.' },
				{ '.', '.', '.', 'p', '.', '.', '.', '.' },
				{ '.', 'b', 'P', 'P', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', 'N', '.', '.' },
				{ 'P', 'P', '.', '.', 'P', 'P', 'P', 'P' },
				{ 'R', 'N', 'B', 'Q', 'K', 'B', '.', 'R' } };
		Character expected = 'b';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by bishop.", expected, actual);
	}

	@Test
	public void test09() {
		char[][] board = { { 'r', 'n', 'b', '.', 'k', '.', 'n', 'r' },
				{ 'p', 'p', 'p', 'p', '.', 'p', 'p', 'p' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', 'b', '.', 'p', '.', '.', '.' },
				{ '.', 'P', '.', '.', 'P', '.', '.', '.' },
				{ 'P', '.', 'N', '.', '.', '.', '.', '.' },
				{ '.', '.', 'P', 'P', '.', 'q', 'P', 'P' },
				{ 'R', '.', 'B', 'Q', 'K', 'B', 'N', 'R' } };
		Character expected = 'q';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by queen.", expected, actual);
	}

	@Test
	public void test10() {
		char[][] board = { { 'n', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'q', '.', '.', '.', '.', '.', '.' },
				{ 'b', '.', '.', '.', '.', '.', '.', '.' },
				{ 'K', '.', '.', '.', '.', '.', '.', 'r' },
				{ 'b', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'k', 'n', 'r', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'r';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by rook.", expected, actual);
	}

	@Test
	public void test11() {
		char[][] board = { { 'n', '.', '.', '.', '.', 'k', '.', '.' },
				{ '.', 'q', '.', '.', '.', '.', '.', '.' },
				{ 'b', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', 'R' },
				{ 'B', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'K', 'n', 'r', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'q';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by queen.", expected, actual);
	}

	@Test
	public void test12() {
		char[][] board = { { '.', '.', 'Q', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', '.', 'N', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'k', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', 'K', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'N';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by knight.", expected, actual);
	}

	@Test
	public void test13() {
		char[][] board = { { '.', '.', 'Q', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'k', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'N', '.', '.', '.', '.', '.', 'b' },
				{ '.', '.', '.', '.', '.', '.', 'K', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'b';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by bishop.", expected, actual);
	}

	@Test
	public void test14() {
		char[][] board = { { '.', '.', 'Q', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ 'P', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', 'q', 'k', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', 'N', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', 'K', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'q';
		Character actual = Chess.getCheck(board);
		assertEquals("White king is in check by queen.", expected, actual);
	}

	@Test
	public void test15() {
		char[][] board = { { '.', '.', '.', '.', 'k', '.', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '.', '.', '.', 'n', '.', 'b', '.' },
				{ '.', '.', '.', '.', 'p', 'r', '.', '.' },
				{ '.', '.', 'r', 'p', 'K', 'p', '.', '.' },
				{ '.', '.', '.', '.', 'b', '.', '.', '.' },
				{ '.', '.', '.', '.', 'q', 'n', '.', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.' } };
		Character expected = 'n';
		Character actual = Chess.getCheck(board);
		assertEquals("Black king is in check by knight.", expected, actual);
	}
}
