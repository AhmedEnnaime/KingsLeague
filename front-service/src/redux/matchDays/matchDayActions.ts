import { createAsyncThunk } from "@reduxjs/toolkit";
import IMatchDay from "../../interfaces/IMatchDay";
import API from "../../utils/API";

export const fetchMatchDaysByTournamentId = createAsyncThunk<
  IMatchDay[],
  number
>("matchDay/all", async (id) => {
  const { data } = await API.get(
    `/ROUND-SERVICE/api/v1/matchDays/tournament/${id}`
  );
  return data;
});
