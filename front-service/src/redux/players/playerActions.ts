import { createAsyncThunk } from "@reduxjs/toolkit";
import API from "../../utils/API";
import { AxiosResponse } from "axios";
import IPlayer from "../../interfaces/IPlayer";

export const fetchAllPlayers = createAsyncThunk<IPlayer[]>(
  "player/all",
  async () => {
    const { data } = await API.get(`/TEAM-SERVICE/api/v1/players`);
    return data;
  }
);

export const createPlayer = createAsyncThunk<IPlayer, Partial<IPlayer>>(
  "player/create",
  async (player) => {
    const { data } = await API.post(`/TEAM-SERVICE/api/v1/players`, player);
    return data;
  }
);

export const deletePlayer = createAsyncThunk<Map<string, string>, number>(
  "player/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/TEAM-SERVICE/api/v1/players/${id}`
    );
    return response.data;
  }
);

export const fetchSelectedPlayer = createAsyncThunk<IPlayer, number>(
  "player/single",
  async (id) => {
    const { data } = await API.get(`/TEAM-SERVICE/api/v1/players/${id}`);
    return data;
  }
);

export const updatePlayer = createAsyncThunk<
  IPlayer,
  { id: number; playerData: Partial<IPlayer> }
>("player/update", async ({ id, playerData }) => {
  const { data } = await API.put(
    `/TEAM-SERVICE/api/v1/players/${id}`,
    playerData
  );
  return data;
});
