import { createSlice } from "@reduxjs/toolkit";
import IPlayer from "../../interfaces/IPlayer";
import {
  createPlayer,
  deletePlayer,
  fetchAllPlayers,
  fetchSelectedPlayer,
  updatePlayer,
} from "./playerActions";

interface PlayerState {
  players: IPlayer[];
  selectedPlayer: IPlayer | null;
  loading: boolean;
}

const initialState: PlayerState = {
  players: [],
  selectedPlayer: null,
  loading: false,
};

const playerSlice = createSlice({
  name: "player",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllPlayers.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllPlayers.fulfilled, (state, action) => {
        state.loading = false;
        state.players = action.payload;
      })
      .addCase(createPlayer.pending, (state) => {
        state.loading = true;
      })
      .addCase(createPlayer.fulfilled, (state, action) => {
        state.loading = false;
        state.players.push(action.payload);
      })
      .addCase(fetchSelectedPlayer.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchSelectedPlayer.fulfilled, (state, action) => {
        state.loading = false;
        state.selectedPlayer = action.payload;
      })
      .addCase(deletePlayer.pending, (state) => {
        state.loading = true;
      })
      .addCase(deletePlayer.fulfilled, (state, action) => {
        state.loading = false;
        state.players = state.players.filter(
          (player) => player.id !== action.meta.arg
        );
      })
      .addCase(updatePlayer.pending, (state) => {
        state.loading = true;
      })
      .addCase(updatePlayer.fulfilled, (state, action) => {
        state.loading = false;
        const updatedPlayerId = action.meta.arg.id;
        const updatedPlayer = action.payload;
        const existingPlayer = state.players.find(
          (player) => player.id === updatedPlayerId
        );
        if (existingPlayer) {
          Object.assign(existingPlayer, updatedPlayer);
        }
      });
  },
  reducers: {},
});

export default playerSlice.reducer;
