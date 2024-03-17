import { createSlice } from "@reduxjs/toolkit";
import IPlayer from "../../interfaces/IPlayer";
import ITeam from "../../interfaces/ITeam";
import ITeamPlayers from "../../interfaces/ITeamPlayers";
import {
  addPlayerToTeam,
  fetchAllTeamPlayers,
  fetchPlayersByTeamId,
  fetchTeamsByPlayerId,
  removePlayerFromTeam,
} from "./teamPlayersActions";

interface TeamPlayerState {
  teamPlayers: ITeamPlayers[];
  teams: ITeam[];
  players: IPlayer[];
  loading: boolean;
}

const initialState: TeamPlayerState = {
  teamPlayers: [],
  teams: [],
  players: [],
  loading: false,
};

const TeamPlayerSlice = createSlice({
  name: "teamPlayer",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllTeamPlayers.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllTeamPlayers.fulfilled, (state, action) => {
        state.loading = false;
        state.teamPlayers = action.payload;
      })
      .addCase(fetchPlayersByTeamId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchPlayersByTeamId.fulfilled, (state, action) => {
        state.loading = false;
        state.players = action.payload;
      })
      .addCase(fetchTeamsByPlayerId.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTeamsByPlayerId.fulfilled, (state, action) => {
        state.loading = false;
        state.teams = action.payload;
      })
      .addCase(addPlayerToTeam.pending, (state) => {
        state.loading = true;
      })
      .addCase(addPlayerToTeam.rejected, (state) => {
        state.loading = true;
      })
      .addCase(addPlayerToTeam.fulfilled, (state, action) => {
        state.teamPlayers.push(action.payload);
      })
      .addCase(removePlayerFromTeam.pending, (state) => {
        state.loading = true;
      })
      .addCase(removePlayerFromTeam.fulfilled, (state, action) => {
        state.loading = false;
        state.teamPlayers = state.teamPlayers.filter(
          (teamPlayer) =>
            JSON.stringify(teamPlayer.id) != JSON.stringify(action.meta.arg)
        );
      });
  },
  reducers: {},
});

export default TeamPlayerSlice.reducer;
