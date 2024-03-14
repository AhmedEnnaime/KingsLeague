import { createAsyncThunk } from "@reduxjs/toolkit";
import ITournament from "../../interfaces/ITournament";
import API from "../../utils/API";
import { AxiosResponse } from "axios";

export const fetchAllTournaments = createAsyncThunk<ITournament[]>(
  "tournament/all",
  async () => {
    const { data } = await API.get(`/TOURNAMENT-SERVICE/api/v1/tournaments`);
    return data;
  }
);

export const fetchTournamentById = createAsyncThunk<ITournament, number>(
  "tournament/single",
  async (id) => {
    const { data } = await API.get(
      `/TOURNAMENT-SERVICE/api/v1/tournaments/${id}`
    );
    return data;
  }
);

export const createTournament = createAsyncThunk<
  ITournament,
  Partial<ITournament>
>("tournament/create", async (tournament) => {
  const { data } = await API.post(
    `/TOURNAMENT-SERVICE/api/v1/tournaments`,
    tournament
  );
  return data;
});

export const deleteTournament = createAsyncThunk<Map<string, string>, number>(
  "tournament/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/TOURNAMENT-SERVICE/api/v1/tournaments/${id}`
    );
    return response.data;
  }
);

export const updateTournament = createAsyncThunk<
  ITournament,
  { id: number; tournamentData: Partial<ITournament> }
>("tournament/update", async ({ id, tournamentData }) => {
  const { data } = await API.put(
    `/TOURNAMENT-SERVICE/api/v1/tournaments/${id}`,
    tournamentData
  );
  return data;
});
