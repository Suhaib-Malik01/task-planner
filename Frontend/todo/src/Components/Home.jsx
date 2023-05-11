import { Box, Grid, Heading, VStack } from "@chakra-ui/layout";
import React from "react";

const Home = () => {
  return (
    <Grid h={"100vh"} templateColumns={"1fr 2fr 2fr 2fr"}>
      <Box border={"1px"}>
        <Heading size={"lg"} fontWeight={"400"} p={"2"}>
          Sprints
        </Heading>
      </Box>
      <Box border={"1px"}>
        <Heading size={"lg"} fontWeight={"400"} p={"2"}>
          To-Do
        </Heading>
      </Box>
      <Box border={"1px"}>
        <Heading size={"lg"} fontWeight={"400"} p={"2"}>
          In-Progress
        </Heading>
      </Box>
      <Box border={"1px"}>
        <Heading size={"lg"} fontWeight={"400"} p={"2"}>
          Completed
        </Heading>
      </Box>
    </Grid>
  );
};

export default Home;
