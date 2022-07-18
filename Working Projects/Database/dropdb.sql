-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'pennys_meal_planner';

DROP DATABASE pennys_meal_planner;

--DROP USER pennys_meal_planner_owner;
--DROP USER pennys_meal_planner_appuser;
