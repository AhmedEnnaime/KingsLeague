import { createSlice } from "@reduxjs/toolkit";
import IResult from "../../interfaces/IResult";
import { createResult } from "./resultActions";

interface ResultState {
  result: IResult | null;
  loading: boolean;
}

const initialState: ResultState = {
  result: null,
  loading: false,
};

const resultSlice = createSlice({
  name: "result",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(createResult.pending, (state) => {
        state.loading = true;
      })
      .addCase(createResult.fulfilled, (state, action) => {
        state.loading = false;
        state.result = action.payload;
      });
  },
  reducers: {},
});

export default resultSlice.reducer;
