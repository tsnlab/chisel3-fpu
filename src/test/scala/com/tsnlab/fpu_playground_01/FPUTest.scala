package com.tsnlab.fpu_playground_01

import chisel3.tester.testableClock
import chiseltest.ChiselScalatestTester
import chiseltest.simulator.WriteVcdAnnotation
import org.scalatest.freespec.AnyFreeSpec

class FPUTest extends AnyFreeSpec with ChiselScalatestTester {
  "Just simulate ;)" in {
    test(new FPUMain()).withAnnotations(Seq(WriteVcdAnnotation)) {
    //test(new FPUMain()) {
      c => {
        for (i <- 0 to 0xFF) {
          c.clock.step()
        }
      }
    }
  }
}
