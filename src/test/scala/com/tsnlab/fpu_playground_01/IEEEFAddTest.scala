package com.tsnlab.fpu_playground_01

import chisel3.Module
import chisel3.tester.testableClock
import chiseltest.{ChiselScalatestTester, testableUInt}
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.freespec.AnyFreeSpec
import fudian.FADD

class IEEEFAddTest extends AnyFreeSpec with ChiselScalatestTester {
  "Just simulate the FPU!" in {
    test(new IEEEFAdd(8,8)).withAnnotations(Seq(WriteVcdAnnotation)) {
      fadd => {
        for (i <- 0 to 0xF) {
          for (j <- 0 to 0xF) {
            fadd.io.a.poke(i << 6)
            fadd.io.b.poke(j << 6)
            fadd.clock.step() // Although we are testing combinational logic, clock must go on
            // TODO: Find better way
          }
        }
      }
    }
  }
}
