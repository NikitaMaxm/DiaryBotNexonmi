package ru.nexonmi.DiaryBotNexonmi.botapi.updates.message;


enum MessageEnum {

    START("command.start", "replay.start"),
    REGISTER_NEW_LESSON("command.register_new_lesson", "replay.register_new_lesson"),
    TIMETABLE_EDIT("command.timetable_edit", "replay.timetable_edit");

    String commandCode;
    String replayCode;

    MessageEnum(String commandCode, String replayCode) {
        this.commandCode = commandCode;
        this.replayCode = replayCode;
    }
}
