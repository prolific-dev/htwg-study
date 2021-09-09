// Rational.java

package beispielprogramme;

import java.util.Scanner;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Formatter;
import java.util.Formattable;
import java.util.regex.Pattern;

import static java.util.FormattableFlags.ALTERNATE;
import static java.util.FormattableFlags.LEFT_JUSTIFY;

import java.util.FormatFlagsConversionMismatchException;
import java.util.IllegalFormatPrecisionException;
import java.util.IllegalFormatException;

public final class Rational extends Number
        implements Comparable<Rational>, Formattable {

    /**
     * The number 0 as Rational.
     */
    public static final Rational ZERO = new Rational(0, 1);

    /**
     * The number 1 as Rational.
     */
    public static final Rational ONE = new Rational(1, 1);

    /**
     * The largest positive value of type Rational, Integer.MAX_VALUE.
     */
    public static final Rational MAX_VALUE
            = new Rational(Integer.MAX_VALUE, 1);

    /**
     * The smallest positive nonzero value of type Rational,
     * 1 / Integer.MAX_VALUE.
     */
    public static final Rational MIN_VALUE
            = new Rational(1, Integer.MAX_VALUE);

    /**
     * The numerator part of the rational number.
     */
    private final int numerator;

    /**
     * The denominator part of the rational number.
     * Always greater or equal 1.
     * The denominator is 1 if numerator is 0.
     */
    private final int denominator;

    /**
     * Rational number with numerator n and denominator d.
     * For denominator 0 an IllegalArgumentException is thrown.
     *
     * @param n the numerator
     * @param d the denominator (must not be 0)
     * @return a Rational with the specified value
     */
    public static Rational valueOf(int n, int d) {
        return new Rational(n, d);
    }

    /**
     * Converts an integer to a rational number.
     * This factory method is provided in preference to an (int) constructor
     * because it allows for reuse of frequently used Rationals.
     *
     * @param n the integer to convert
     * @return a Rational with the specified value
     */
    public static Rational valueOf(int n) {
        switch (n) {
            case 0:
                return ZERO;
            case 1:
                return ONE;
            case Integer.MAX_VALUE:
                return MAX_VALUE;
            default:
                return new Rational(n, 1);
        }
    }

    /**
     * Converts a string to a rational number.
     * The string must have the format specified by toString.
     * Otherwise NumberFormatException is thrown.
     *
     * @param s the string to convert
     * @return a Rational with the specified value
     */
    public static Rational valueOf(String s) {
        Scanner sc = new Scanner(s);
        if (!sc.hasNext(FORMAT)) {
            throw new NumberFormatException(s);
        }

        sc.useDelimiter("/");
        return new Rational(sc.nextInt(), sc.nextInt());
    }

    private static final Pattern FORMAT = Pattern.compile("^[+-]?\\d+/\\d+$");

    private Rational(long n, long d) {
        if (d == 0) {
            throw new IllegalArgumentException("invalid denominator 0");
        }

        if (n == 0) {
            this.numerator = 0;
            this.denominator = 1;
            return;
        }

        int sign = Long.signum(n) * Long.signum(d);
        long nn = Math.abs(n);
        long dd = Math.abs(d);

        if (dd != 1) {
            long f = gcd(nn, dd);
            nn /= f;
            dd /= f;
        }

        if (nn > Integer.MAX_VALUE) {
            throw new ArithmeticException(
                    String.format("numerator overflow: %d/%d", sign * nn, dd));
        }

        if (dd > Integer.MAX_VALUE) {
            throw new ArithmeticException(
                    String.format("denominator overflow: %d/%d", sign * nn, dd));
        }

        this.numerator = sign * (int) nn;
        this.denominator = (int) dd;
    }

    /**
     * Computes the greates common divisor using Euclids algorithm.
     * For negative numbers the result is undefined.
     *
     * @param a a positive number
     * @param b a positive number
     * @return the gcd of a and b
     */
    private static long gcd(long a, long b) {
        while (a > 0 && b > 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }

        return Math.max(a, b);
    }

    /**
     * Returns the numerator part of the reduced rational number.
     *
     * @return the numerator
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * Returns the denominator part of the reduced rational number.
     *
     * @return the denominator
     */
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Checks for a positive value.
     *
     * @return true if the rational is greater than or equal 0, false else
     */
    public boolean isPositive() {
        return this.numerator >= 0;
    }

    /**
     * Checks for a negative value.
     *
     * @return true if the rational is lower than 0, false else
     */
    public boolean isNegative() {
        return this.numerator < 0;
    }

    /**
     * Returns a Rational with inverted sign.
     *
     * @return -this
     */
    public Rational negate() {
        return new Rational(-this.numerator, this.denominator);
    }

    /**
     * Returns a Rational with numerator and denominator swapped.
     *
     * @return 1/this
     */
    public Rational invert() {
        return new Rational(this.denominator, this.numerator);
    }

    /**
     * Returns a Rational whose value is (this + r).
     *
     * @param r value to be added to this Rational
     * @return this + r
     */
    public Rational add(Rational r) {
        if (this.denominator == r.denominator) {
            return new Rational((long) this.numerator + r.numerator,
                    this.denominator);
        }

        long n = (long) this.numerator * r.denominator
                + (long) r.numerator * this.denominator;
        long d = (long) this.denominator * r.denominator;
        return new Rational(n, d);
    }

    /**
     * Returns a Rational whose value is (this - r).
     *
     * @param r value to be subtracted from this Rational
     * @return this - r
     */
    public Rational subtract(Rational r) {
        if (this.denominator == r.denominator) {
            return new Rational((long) this.numerator - r.numerator,
                    this.denominator);
        }

        long n = (long) this.numerator * r.denominator
                - (long) r.numerator * this.denominator;
        long d = (long) this.denominator * r.denominator;
        return new Rational(n, d);
    }

    /**
     * Returns a Rational whose value is (this * r).
     *
     * @param r value to be multiplied by this Rational
     * @return this * r
     */
    public Rational multiply(Rational r) {
        long n = (long) this.numerator * r.numerator;
        long d = (long) this.denominator * r.denominator;
        return new Rational(n, d);
    }

    /**
     * Returns a Rational whose value is (this / r).
     *
     * @param r value by which this Rational is to be divided
     * @return this / r
     */
    public Rational divide(Rational r) {
        if (r.numerator == 0) {
            throw new IllegalArgumentException("invalid divisor: " + r);
        }

        long n = (long) this.numerator * r.denominator;
        long d = (long) this.denominator * r.numerator;
        return new Rational(n, d);
    }

    /**
     * Returns a string representation of this Rational.
     * The string decimal representation including sign of the numerator
     * is followed by '/' and the string representation of the denominator.
     *
     * @return string representation of this Rational.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.numerator)
                .append('/')
                .append(this.denominator)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rational) {
            Rational r = (Rational) o;
            return this.numerator == r.numerator
                    && this.denominator == r.denominator;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.numerator * 31 + this.denominator;
    }

    @Override
    public int intValue() {
        return (int) ((double) this.numerator / this.denominator);
    }

    @Override
    public long longValue() {
        return (long) ((double) this.numerator / this.denominator);
    }

    @Override
    public double doubleValue() {
        return (double) this.numerator / this.denominator;
    }

    @Override
    public float floatValue() {
        return (float) this.doubleValue();
    }

    @Override
    public int compareTo(Rational r) {
        if (this.denominator == r.denominator) {
            if (this.numerator < r.numerator) {
                return -1;
            }

            if (this.numerator == r.numerator) {
                return 0;
            }

            return 1;
        }

        if ((long) this.numerator * r.denominator
                < (long) r.numerator * this.denominator) {
            return -1;
        }

        return 1;
    }

    @Override
    public void formatTo(Formatter f, int flags, int width, int precision) {

        if (precision != -1) {
            throw new IllegalFormatPrecisionException(precision);
        }

        if ((flags & ALTERNATE) == ALTERNATE) {
            throw new FormatFlagsConversionMismatchException("#", 's');
        }

        String s = this.toString();

        if (width <= s.length()) {
            f.format(s);
            return;
        }

        if ((flags & LEFT_JUSTIFY) == LEFT_JUSTIFY) {
            f.format("%-" + width + "s", s);
            return;
        }

        f.format("%" + width + "s", s);
    }
}

/**
 * Test driver.
 */
final class RationalTest {
    private RationalTest() {
    }

    /**
     * Evaluates an arithmetic postfix expression with rational numbers.
     * Example: java RationalTest 1/2 3/4 add 5/6 multiply
     *
     * @param args the arithmetic postfix expression with rational numbers
     */
    public static void main(String[] args) {
        Rational[] stack = new Rational[args.length];
        int top = 0;

        Locale.setDefault(Locale.ENGLISH);
        Scanner input = new Scanner(System.in);

        for (String arg : args) {
            try {
                switch (arg) {
                    case "stackdump":
                        for (int j = 0; j < top; ++j) {
                            System.out.printf("%d: %s (%.15e)%n",
                                    j, stack[j], stack[j].doubleValue());
                        }
                        continue;
                    case "negate":
                        stack[top - 1] = stack[top - 1].negate();
                        break;
                    case "invert":
                        stack[top - 1] = stack[top - 1].invert();
                        break;
                    case "add":
                        stack[top - 2] = stack[top - 2].add(stack[top - 1]);
                        --top;
                        break;
                    case "subtract":
                        stack[top - 2] = stack[top - 2].subtract(stack[top - 1]);
                        --top;
                        break;
                    case "multiply":
                        stack[top - 2] = stack[top - 2].multiply(stack[top - 1]);
                        --top;
                        break;
                    case "divide":
                        stack[top - 2] = stack[top - 2].divide(stack[top - 1]);
                        --top;
                        break;

                    case "getNumerator":
                        System.out.printf("%s: %d%n",
                                arg, stack[top - 1].getNumerator());
                        continue;
                    case "getDenominator":
                        System.out.printf("%s: %d%n",
                                arg, stack[top - 1].getDenominator());
                        continue;
                    case "isPositive":
                        System.out.printf("%s: %b%n",
                                arg, stack[top - 1].isPositive());
                        continue;
                    case "isNegative":
                        System.out.printf("%s: %b%n",
                                arg, stack[top - 1].isNegative());
                        continue;

                    case "toString":
                        System.out.printf("%s: %s%n",
                                arg, stack[top - 1].toString());
                        continue;
                    case "hashCode":
                        System.out.printf("%s: %d%n",
                                arg, stack[top - 1].hashCode());
                        continue;
                    case "equals":
                        System.out.printf("%s: %b%n",
                                arg,
                                stack[top - 2].equals(stack[top - 1]));
                        continue;

                    case "intValue":
                        System.out.printf("%s: %d%n",
                                arg, stack[top - 1].intValue());
                        continue;
                    case "longValue":
                        System.out.printf("%s: %d%n",
                                arg, stack[top - 1].longValue());
                        continue;
                    case "doubleValue":
                        System.out.printf("%s: %.15e%n",
                                arg, stack[top - 1].doubleValue());
                        continue;
                    case "floatValue":
                        System.out.printf("%s: %.7e%n",
                                arg, stack[top - 1].floatValue());
                        continue;

                    case "compareTo":
                        System.out.printf("%s: %d%n",
                                arg,
                                stack[top - 2].compareTo(stack[top - 1]));
                        continue;

                    case "formatTo":
                        System.err.print("Enter format string: ");
                        try {
                            System.out.printf("%s: " + input.next() + "%n",
                                    arg, stack[top - 1]);
                        } catch (NoSuchElementException x) {
                            System.err.println("no input");
                            System.exit(1);
                        }
                        continue;

                    case "ZERO":
                        stack[top++] = Rational.ZERO;
                        continue;
                    case "ONE":
                        stack[top++] = Rational.ONE;
                        continue;
                    case "MAX_VALUE":
                        stack[top++] = Rational.MAX_VALUE;
                        continue;
                    case "MIN_VALUE":
                        stack[top++] = Rational.MIN_VALUE;
                        continue;

                    case "int":
                        System.err.print("Enter integer: ");
                        try {
                            stack[top] = Rational.valueOf(input.nextInt());
                            ++top;
                        } catch (NoSuchElementException x) {
                            System.err.println(input.next() + ": invalid input");
                            System.exit(1);
                        }
                        continue;
                    case "intint":
                        System.err.print("Enter numerator and denominator: ");
                        try {
                            stack[top] = Rational.valueOf(input.nextInt(),
                                    input.nextInt());
                            ++top;
                        } catch (NoSuchElementException x) {
                            System.err.println(input.next() + ": invalid input");
                            System.exit(1);
                        }
                        continue;
                    default:
                        stack[top] = Rational.valueOf(arg);
                        ++top;
                        continue;
                }

                System.out.println(arg);

            } catch (ArrayIndexOutOfBoundsException x) {
                System.err.println(arg + ": missing operand");
                System.exit(1);
            } catch (NumberFormatException x) {
                System.err.println(x.getMessage() + ": not a rational number");
            } catch (ArithmeticException x) {
                System.err.println(x.getMessage());
            } catch (IllegalFormatException x) {
                System.err.printf("%s: %s%n", arg, x);
            } catch (IllegalArgumentException x) {
                System.err.println(x.getMessage());
            }
        }

        for (int j = 0; j < top; ++j) {
            System.out.printf("%d: %s (%.15e)%n",
                    j, stack[j], stack[j].doubleValue());
        }
    }
}

