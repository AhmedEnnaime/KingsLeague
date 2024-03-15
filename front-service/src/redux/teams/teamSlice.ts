import { createSlice } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import { fetchAllTeams } from "./teamActions";

interface TeamState {
  teams: ITeam[];
  loading: boolean;
}

const initialState: TeamState = {
  teams: [],
  loading: false,
};

const teamSlice = createSlice({
  name: "team",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllTeams.pending, (state) => {
        state.loading = false;
      })
      .addCase(fetchAllTeams.fulfilled, (state, action) => {
        state.loading = true;
        state.teams = action.payload;
      });
  },
  reducers: {},
});

export default teamSlice.reducer;
