import { createAsyncThunk } from "@reduxjs/toolkit";
import IPlayer from "../../interfaces/IPlayer";
import API from "../../utils/API";
import ITeamPlayers from "../../interfaces/ITeamPlayers";
import { TeamPlayerKey } from "../../embddables/TeamPlayerKey";
import { AxiosResponse } from "axios";
import ITeam from "../../interfaces/ITeam";

export const fetchAllTeamPlayers = createAsyncThunk<ITeamPlayers[]>(
  "teamPlayer/all",
  async () => {
    const { data } = await API.get(`/TEAM-SERVICE/api/v1/teamPlayer`);
    return data;
  }
);

export const fetchPlayersByTeamId = createAsyncThunk<IPlayer[], number>(
  "team/players",
  async (teamId) => {
    const { data } = await API.get(
      `/TEAM-SERVICE/api/v1/teamPlayer/players/${teamId}`
    );
    return data;
  }
);

export const fetchTeamsByPlayerId = createAsyncThunk<ITeam[], number>(
  "player/teams",
  async (playerId) => {
    const { data } = await API.get(
      `/TEAM-SERVICE/api/v1/teamPlayer/teams/${playerId}`
    );
    return data;
  }
);

export const addPlayerToTeam = createAsyncThunk<
  ITeamPlayers,
  Partial<ITeamPlayers>
>("teamPlayer/register", async (teamPlayer) => {
  const { data } = await API.post(
    `/TEAM-SERVICE/api/v1/teamPlayer`,
    teamPlayer
  );
  return data;
});

export const removePlayerFromTeam = createAsyncThunk<
  Map<string, string>,
  TeamPlayerKey
>("teamPlayer/remove", async (id) => {
  const response: AxiosResponse = await API.delete(
    `/TEAM-SERVICE/api/v1/teamPlayer/team/${id.teamId}/player/${id.playerId}`
  );
  return response.data;
});
