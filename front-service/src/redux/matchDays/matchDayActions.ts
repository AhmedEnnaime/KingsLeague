import { createAsyncThunk } from "@reduxjs/toolkit";
import IMatchDay from "../../interfaces/IMatchDay";
import API from "../../utils/API";

export const fetchMatchDaysByTournamentId = createAsyncThunk<
  IMatchDay[],
  number
>("matchDay/all", async (id) => {
  const { data } = await API.get(
    `/MATCHDAY-SERVICE/api/v1/matchDays/tournament/${id}`
  );
  return data;
});

export const createMatchDay = createAsyncThunk<IMatchDay, Partial<IMatchDay>>(
  "matchDay/create",
  async (matchDay) => {
    const { data } = await API.post(
      `/MATCHDAY-SERVICE/api/v1/matchDays`,
      matchDay
    );
    return data;
  }
);
