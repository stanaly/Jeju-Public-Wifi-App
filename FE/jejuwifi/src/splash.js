import React, { useEffect } from "react";
import { Box, Typography, TextField, Button, ButtonBase } from "@mui/material";
import Image from "mui-image";
import splash from "./asset/splash.png";

export default function Splash() {
  const ref = React.useRef(null);

  useEffect(() => {
    setTimeout(() => {
      window.location.href = "/login";
    }, 3000);
  }, []);

  useEffect(() => {
    const element = ref.current;
    element.style.transition = "opacity 2s";
    setTimeout(() => {
      element.style.opacity = 1;
    }, 100);
  }, []);

  return (
    <Box
      ref={ref}
      sx={{
        display: "flex",
        justifyContent: "center",
        height: "100vh",
        alignItems: "center",
        opacity: 0,
      }}
    >
      <Box sx={{ marginBottom: "300px" }}>
        <Image src={splash} duration={0} height={88} width={426}></Image>
      </Box>
    </Box>
  );
}
