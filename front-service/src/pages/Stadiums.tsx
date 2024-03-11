import { useState } from "react";
import StadiumCard from "../components/StadiumCard";
import Button from "../shared/Button";
import Header from "../shared/Header";
import StadiumModal from "../components/StadiumModal";

const Stadiums = () => {
  const [open, setOpen] = useState(false);
  return (
    <>
      <Header />
      <div className="flex justify-between p-4">
        <h1 className="text-2xl font-semibold">Stadiums</h1>
        <Button
          onClick={() => {
            setOpen(true);
          }}
          content="Create Stadium"
        />
      </div>

      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 justify-center pt-4 items-center">
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
        <StadiumCard />
      </div>
      {open ? <StadiumModal open={open} setOpen={setOpen} /> : ""}
    </>
  );
};

export default Stadiums;
