package ch.fhnw.tictactoe.logic;

import javax.vecmath.Matrix3f;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Fabrizio on 08.12.2015.
 *
 */
public class EndStates {
    public final List<Matrix3f> winMatrixes = Arrays.asList(
            new Matrix3f(
                    1, 1, 1,
                    0, 0, 0,
                    0, 0, 0),

            new Matrix3f(
                    0, 0, 0,
                    1, 1, 1,
                    0, 0, 0),

            new Matrix3f(
                    0, 0, 0,
                    0, 0, 0,
                    1, 1, 1),

            new Matrix3f(
                    1, 0, 0,
                    1, 0, 0,
                    1, 0, 0),

            new Matrix3f(
                    0, 1, 0,
                    0, 1, 0,
                    0, 1, 0),

            new Matrix3f(
                    0, 0, 1,
                    0, 0, 1,
                    0, 0, 1),

            new Matrix3f(
                    0, 0, 1,
                    0, 1, 0,
                    1, 0, 0),

            new Matrix3f(
                    1, 0, 0,
                    0, 1, 0,
                    0, 0, 1)

    );
}
