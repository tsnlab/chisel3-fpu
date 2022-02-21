package com.tsnlab.fpu_playground_01

import chisel3._
import chisel3.stage.{ChiselStage, ChiselGeneratorAnnotation}

class FPUMain() extends Module {
  val io = IO(new Bundle {
    val output           = Output(UInt(32.W))
  })

  val cnt = RegInit(0.U(32.W)) // 32-bit clock divider
  cnt := cnt + 1.U;

  io.output := cnt;
}

object FPUMain extends App {
  // EmitVerilog
  (new ChiselStage).emitVerilog(new FPUMain,Array("-td","vout"))

  // generate graph files for circuit visualization
  (new layered.stage.ElkStage).execute(
    Array("-td", "vout", "--lowFir"),
    Seq(ChiselGeneratorAnnotation(() => new FPUMain))
  )
}
