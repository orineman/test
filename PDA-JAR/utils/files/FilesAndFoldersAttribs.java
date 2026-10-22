package com.infowave.pda.utils.files;

import java.util.regex.*;

/**
 * Created by IntelliJ IDEA.
 * User vlanik
 * Date= 08.10.2007
 * Time= 19=28=54
 * To change this template use File | Settings | File Templates.
 */
public class FilesAndFoldersAttribs {

    // The specified items can be browsed in place.
    public static final long BROWSABLE = 0x08000000;
    // The specified items can be deleted by selecting Delete from their context menus.
    public static final long CANDELETE = 0x00000020;
    // The specified items can be renamed.
    // Note that this flag is essentially a suggestion. It does not guarantee
    // that a namespace client will rename the file or folder object.
    public static final long CANRENAME = 0x00000010;
    // Do not use.
    public static final long CAPABILITYMASK = 0x00000177;
    // The specified items are compressed.
    public static final long COMPRESSED = 0x04000000;
    // Do not use.
    public static final long CONTENTSMASK = 0x80000000;
    // Do not use.
    public static final long DISPLAYATTRMASK = 0x000FC000;
    // The specified items are drop targets.
    public static final long DROPTARGET = 0x00000100;
    // The item is encrypted and may require special presentation.
    public static final long ENCRYPTED = 0x00002000;
    // The specified folder objects are either file system folders
    // or have at least one descendant (child; grandchild; or later)
    // that is a file system folder.
    public static final long FILESYSANCESTOR = 0x10000000;
    // The specified items are part of the file system
    // (that is; they are files; directories; or root directories).
    // The parsed names of the items can be assumed to be valid Win32 file system paths.
    // These paths can be either Universal Naming Convention (UNC) or drive-letter based.
    public static final long FILESYSTEM = 0x40000000;
    // The specified items are folders.
    // Some items can be flagged with both STREAM and FOLDER; such as a .zip file.
    // Some applications may include this flag when testing for items
    // that are both files and containers.
    public static final long FOLDER = 0x20000000;
    // The specified items should be displayed using a ghosted icon.
    public static final long GHOSTED = 0x00008000;
    // The specified items have property sheets.
    public static final long HASPROPSHEET = 0x00000040;
    // The specified folder objects may have subfolders and are;
    // therefore; expandable in the left pane of Windows Explorer.
    // The HASSUBFOLDER attribute is only advisory and may be returned
    // by Shell folder implementations even if they do not contain subfolders.
    // Note; however; that the converse вЂ” failing to return HASSUBFOLDER вЂ”
    // definitively states that the folder objects do not have subfolders.
    public static final long HASSUBFOLDER = 0x80000000;
    // The item is hidden and should not be displayed
    // unless the Show hidden files and folders option is enabled in Folder Settings.
    public static final long HIDDEN = 0x00080000;
    // Indicates that accessing the object is a slow operation.
    // Applications should avoid accessing items flagged with ISSLOW.
    public static final long ISSLOW = 0x00004000;
    // The specified items are shortcuts.
    public static final long LINK = 0x00010000;
    // The specified items contain new content.
    public static final long NEWCONTENT = 0x00200000;
    // The specified items are nonenumerated items.
    public static final long NONENUMERATED = 0x00100000;
    // The specified items are read-only. In the case of folders;
    // this means that new items cannot be created in those folders.
    public static final long READONLY = 0x00040000;
    // The specified items are on removable media or are themselves removable devices.
    public static final long REMOVABLE = 0x02000000;
    // The specified folder objects are shared.
    public static final long SHARE = 0x00020000;
    // See MSDN.
    public static final long STORAGE = 0x00000008;
    // See MSDN.
    public static final long STORAGEANCESTOR = 0x00800000;
    // This flag is a mask for the storage capability attributes.
    public static final long STORAGECAPMASK = 0x70C50008;
    // Indicates that the item has a stream associated with it. See MSDN.
    public static final long STREAM = 0x00400000;
    // See MSDN.
    public static final long VALIDATE = 0x01000000;


    /**
     * Tests whether this file attributes represent an object that can be deleted.
     *
     * @return {Boolean} <code>true</code> if this file attributes represent an object that can be deleted,
     *         <code>false</code> - otherwise.
     */
    public static boolean isDeletable(final long attributes) {
        return ((attributes & CANDELETE)
                == CANDELETE);
    }

    /**
     * Tests whether this file attributes represent a directory,
     * i.e. a real folder that is a part of file system.
     *
     * @return {Boolean} <code>true</code> if this file attributes represent a directory,
     *         <code>false</code> - otherwise.
     */
    public static boolean isDirectory(final long attributes) {
        return FilesAndFoldersAttribs.isFolder(attributes) && FilesAndFoldersAttribs.isFileSystemPart(attributes);
    }

    /**
     * Tests whether this file attributes represent a folder.
     *
     * @return {Boolean} <code>true</code> if this file attributes represent a folder,
     *         <code>false</code> - otherwise.
     */
    public static boolean isFolder(final long attributes) {
//    return ((this.attributes & LLE.Services.FileSystem.FILE_ATTRIBUTE.FOLDER)
//                === LLE.Services.FileSystem.FILE_ATTRIBUTE.FOLDER);
        return (((attributes & FOLDER)
                == FOLDER)
                && ((attributes & STREAM)
                != STREAM));
    }

    /**
     * Tests whether this file attributes represent a file.
     *
     * @return {Boolean} <code>true</code> if this file attributes represent a file,
     *         <code>false</code> - otherwise.
     */
    public static boolean isFile(final long attributes) {
        return (attributes > 0 && !isFolder(attributes));
    }

    /**
     * Tests whether this file attributes represent a part of file system
     * (that is, a file, directory, or root directory).
     *
     * @return {Boolean} <code>true</code> if this file attributes represent a part of file system,
     *         <code>false</code> - otherwise.
     */
    public static boolean isFileSystemPart(final long attributes) {
        return ((attributes & FILESYSTEM)
                == FILESYSTEM);
    }

    /**
     * Tests whether this file attributes represent an object that can be renamed.
     *
     * @return {Boolean} <code>true</code> if this file attributes represent an object that can be renamed,
     *         <code>false</code> - otherwise.
     */
    public static boolean isRenamable(final long attributes) {
        return ((attributes & CANRENAME)
                == CANRENAME);
    }


    public static Boolean isSystemFolder(final long attributes) {
        return isFolder(attributes) && !isFileSystemPart(attributes);
    }

    public static String getFormatedSize(final String str_size) {
        long GB_SIZE = 1024 * 1024 * 1024;
        long MB_SIZE = 1024 * 1024;
        long KB_SIZE = 1024;
        Long retSize;

        Pattern pattern = Pattern.compile("^\\d*$");
        Matcher matcher = pattern.matcher(str_size);
        if (matcher.matches()) {
            Long size = null;
            try {
                size = Long.parseLong(str_size);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "";
            }
            if (size / GB_SIZE > 0) {
                retSize = size / GB_SIZE;
                return retSize.toString() + " GB";
            }
            if (size / MB_SIZE > 0) {
                retSize = size / MB_SIZE;
                return retSize.toString() + " MB";
            }
            if (size / KB_SIZE > 0) {
                retSize = size / KB_SIZE;
                return retSize.toString() + " KB";
            }

            retSize = size;
            return retSize.toString() + " B";
        }

        return str_size;
    }
}
