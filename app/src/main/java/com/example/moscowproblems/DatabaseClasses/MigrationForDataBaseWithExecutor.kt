package com.example.moscowproblems.DatabaseClasses

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class MigrationForDataBaseWithExecutor: Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE ProblemModel ADD COLUMN executor TEXT NOT NULL DEFAULT ''"
        )
    }
}