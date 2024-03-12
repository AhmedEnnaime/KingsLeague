import { createSlice } from "@reduxjs/toolkit";
import IStadium from "../../interfaces/IStadium";
import {
  createStadium,
  deleteStadium,
  fetchAllStadiums,
} from "./stadiumActions";

interface StadiumState {
  stadiums: IStadium[];
  selectedStadiumID: number | null;
  loading: boolean;
}

const initialState: StadiumState = {
  stadiums: [],
  selectedStadiumID: null,
  loading: false,
};

const stadiumSlice = createSlice({
  name: "stadium",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllStadiums.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllStadiums.fulfilled, (state, action) => {
        state.loading = false;
        state.stadiums = action.payload;
      })
      .addCase(createStadium.pending, (state) => {
        state.loading = true;
      })
      .addCase(createStadium.fulfilled, (state, action) => {
        state.loading = false;
        state.stadiums.push(action.payload);
      })
      .addCase(deleteStadium.pending, (state, action) => {
        state.loading = true;
        state.stadiums = state.stadiums.filter(
          (stadium) => stadium.id !== action.meta.arg
        );
      });
  },
  reducers: {},
});

export default stadiumSlice.reducer;
