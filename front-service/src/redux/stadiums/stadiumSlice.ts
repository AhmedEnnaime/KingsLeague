import { createSlice } from "@reduxjs/toolkit";
import IStadium from "../../interfaces/IStadium";
import {
  createStadium,
  deleteStadium,
  fetchAllStadiums,
  updateStadium,
} from "./stadiumActions";

interface StadiumState {
  stadiums: IStadium[];
  loading: boolean;
}

const initialState: StadiumState = {
  stadiums: [],
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
      .addCase(deleteStadium.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteStadium.fulfilled, (state, action) => {
        state.loading = false;
        state.stadiums = state.stadiums.filter(
          (stadium) => stadium.id !== action.meta.arg
        );
      })
      .addCase(updateStadium.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateStadium.fulfilled, (state, action) => {
        state.loading = false;
        const updatedStadiumId = action.meta.arg.id;
        const updatedStadium = action.payload;
        const existingStadium = state.stadiums.find(
          (stadium) => stadium.id === updatedStadiumId
        );
        if (existingStadium) {
          Object.assign(existingStadium, updatedStadium);
        }
      });
  },
  reducers: {},
});

export default stadiumSlice.reducer;
