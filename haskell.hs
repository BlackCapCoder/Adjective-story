storyFromList  = withAdjs   id
storyFromDict  = withAdjs   \x -> [ w | [w, t] <- words <$> x, t=="adj" ]
storyFromSTDIN = writeStory getLine

writeStory getAdj storyPth outPth
  = writeFile outPth . unwords =<< ( do
      story <- words <$> readFile storyPth
      forM story $ \case
          "ADJEKTIV" -> getAdj
          x          -> return x
      )

withAdjs f storyPth adjPth outPth = do
  adj <- f . lines <$> readFile adjPth
  writeStory ((adj!!) <$> randomRIO (0, length adj - 1)) storyPth outPth
